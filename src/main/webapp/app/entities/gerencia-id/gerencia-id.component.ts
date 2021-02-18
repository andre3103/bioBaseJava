import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IGerenciaId } from 'app/shared/model/gerencia-id.model';
import { GerenciaIdService } from './gerencia-id.service';
import { GerenciaIdDeleteDialogComponent } from './gerencia-id-delete-dialog.component';

@Component({
  selector: 'jhi-gerencia-id',
  templateUrl: './gerencia-id.component.html',
})
export class GerenciaIdComponent implements OnInit, OnDestroy {
  gerenciaIds?: IGerenciaId[];
  eventSubscriber?: Subscription;

  constructor(protected gerenciaIdService: GerenciaIdService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.gerenciaIdService.query().subscribe((res: HttpResponse<IGerenciaId[]>) => (this.gerenciaIds = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInGerenciaIds();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IGerenciaId): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInGerenciaIds(): void {
    this.eventSubscriber = this.eventManager.subscribe('gerenciaIdListModification', () => this.loadAll());
  }

  delete(gerenciaId: IGerenciaId): void {
    const modalRef = this.modalService.open(GerenciaIdDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.gerenciaId = gerenciaId;
  }
}
