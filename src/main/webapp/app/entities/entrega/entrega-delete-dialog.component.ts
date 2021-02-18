import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEntrega } from 'app/shared/model/entrega.model';
import { EntregaService } from './entrega.service';

@Component({
  templateUrl: './entrega-delete-dialog.component.html',
})
export class EntregaDeleteDialogComponent {
  entrega?: IEntrega;

  constructor(protected entregaService: EntregaService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.entregaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('entregaListModification');
      this.activeModal.close();
    });
  }
}
