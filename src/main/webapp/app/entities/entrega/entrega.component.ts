import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEntrega } from 'app/shared/model/entrega.model';
import { EntregaService } from './entrega.service';
import { EntregaDeleteDialogComponent } from './entrega-delete-dialog.component';

@Component({
  selector: 'jhi-entrega',
  templateUrl: './entrega.component.html',
})
export class EntregaComponent implements OnInit, OnDestroy {
  entregas?: IEntrega[];
  eventSubscriber?: Subscription;

  constructor(protected entregaService: EntregaService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.entregaService.query().subscribe((res: HttpResponse<IEntrega[]>) => (this.entregas = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEntregas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEntrega): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEntregas(): void {
    this.eventSubscriber = this.eventManager.subscribe('entregaListModification', () => this.loadAll());
  }

  delete(entrega: IEntrega): void {
    const modalRef = this.modalService.open(EntregaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.entrega = entrega;
  }
}
