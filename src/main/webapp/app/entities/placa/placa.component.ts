import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPlaca } from 'app/shared/model/placa.model';
import { PlacaService } from './placa.service';
import { PlacaDeleteDialogComponent } from './placa-delete-dialog.component';

@Component({
  selector: 'jhi-placa',
  templateUrl: './placa.component.html',
})
export class PlacaComponent implements OnInit, OnDestroy {
  placas?: IPlaca[];
  eventSubscriber?: Subscription;

  constructor(protected placaService: PlacaService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.placaService.query().subscribe((res: HttpResponse<IPlaca[]>) => (this.placas = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPlacas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPlaca): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPlacas(): void {
    this.eventSubscriber = this.eventManager.subscribe('placaListModification', () => this.loadAll());
  }

  delete(placa: IPlaca): void {
    const modalRef = this.modalService.open(PlacaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.placa = placa;
  }
}
