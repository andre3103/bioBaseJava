import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGerenciaId } from 'app/shared/model/gerencia-id.model';
import { GerenciaIdService } from './gerencia-id.service';

@Component({
  templateUrl: './gerencia-id-delete-dialog.component.html',
})
export class GerenciaIdDeleteDialogComponent {
  gerenciaId?: IGerenciaId;

  constructor(
    protected gerenciaIdService: GerenciaIdService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.gerenciaIdService.delete(id).subscribe(() => {
      this.eventManager.broadcast('gerenciaIdListModification');
      this.activeModal.close();
    });
  }
}
