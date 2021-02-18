import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRecebimento } from 'app/shared/model/recebimento.model';
import { RecebimentoService } from './recebimento.service';

@Component({
  templateUrl: './recebimento-delete-dialog.component.html',
})
export class RecebimentoDeleteDialogComponent {
  recebimento?: IRecebimento;

  constructor(
    protected recebimentoService: RecebimentoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.recebimentoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('recebimentoListModification');
      this.activeModal.close();
    });
  }
}
