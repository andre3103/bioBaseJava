import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBarcode } from 'app/shared/model/barcode.model';
import { BarcodeService } from './barcode.service';

@Component({
  templateUrl: './barcode-delete-dialog.component.html',
})
export class BarcodeDeleteDialogComponent {
  barcode?: IBarcode;

  constructor(protected barcodeService: BarcodeService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.barcodeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('barcodeListModification');
      this.activeModal.close();
    });
  }
}
