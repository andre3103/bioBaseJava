import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISequenciamento } from 'app/shared/model/sequenciamento.model';
import { SequenciamentoService } from './sequenciamento.service';

@Component({
  templateUrl: './sequenciamento-delete-dialog.component.html',
})
export class SequenciamentoDeleteDialogComponent {
  sequenciamento?: ISequenciamento;

  constructor(
    protected sequenciamentoService: SequenciamentoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.sequenciamentoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('sequenciamentoListModification');
      this.activeModal.close();
    });
  }
}
