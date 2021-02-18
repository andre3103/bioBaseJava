import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPlaca } from 'app/shared/model/placa.model';
import { PlacaService } from './placa.service';

@Component({
  templateUrl: './placa-delete-dialog.component.html',
})
export class PlacaDeleteDialogComponent {
  placa?: IPlaca;

  constructor(protected placaService: PlacaService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.placaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('placaListModification');
      this.activeModal.close();
    });
  }
}
