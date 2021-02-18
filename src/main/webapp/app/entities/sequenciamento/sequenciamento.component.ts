import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISequenciamento } from 'app/shared/model/sequenciamento.model';
import { SequenciamentoService } from './sequenciamento.service';
import { SequenciamentoDeleteDialogComponent } from './sequenciamento-delete-dialog.component';

@Component({
  selector: 'jhi-sequenciamento',
  templateUrl: './sequenciamento.component.html',
})
export class SequenciamentoComponent implements OnInit, OnDestroy {
  sequenciamentos?: ISequenciamento[];
  eventSubscriber?: Subscription;

  constructor(
    protected sequenciamentoService: SequenciamentoService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.sequenciamentoService.query().subscribe((res: HttpResponse<ISequenciamento[]>) => (this.sequenciamentos = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInSequenciamentos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISequenciamento): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSequenciamentos(): void {
    this.eventSubscriber = this.eventManager.subscribe('sequenciamentoListModification', () => this.loadAll());
  }

  delete(sequenciamento: ISequenciamento): void {
    const modalRef = this.modalService.open(SequenciamentoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.sequenciamento = sequenciamento;
  }
}
