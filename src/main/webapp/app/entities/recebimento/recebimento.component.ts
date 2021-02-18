import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IRecebimento } from 'app/shared/model/recebimento.model';
import { RecebimentoService } from './recebimento.service';
import { RecebimentoDeleteDialogComponent } from './recebimento-delete-dialog.component';

@Component({
  selector: 'jhi-recebimento',
  templateUrl: './recebimento.component.html',
})
export class RecebimentoComponent implements OnInit, OnDestroy {
  recebimentos?: IRecebimento[];
  eventSubscriber?: Subscription;

  constructor(
    protected recebimentoService: RecebimentoService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.recebimentoService.query().subscribe((res: HttpResponse<IRecebimento[]>) => (this.recebimentos = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInRecebimentos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IRecebimento): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInRecebimentos(): void {
    this.eventSubscriber = this.eventManager.subscribe('recebimentoListModification', () => this.loadAll());
  }

  delete(recebimento: IRecebimento): void {
    const modalRef = this.modalService.open(RecebimentoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.recebimento = recebimento;
  }
}
