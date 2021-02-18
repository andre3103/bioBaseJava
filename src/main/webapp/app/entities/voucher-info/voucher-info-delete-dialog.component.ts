import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IVoucherInfo } from 'app/shared/model/voucher-info.model';
import { VoucherInfoService } from './voucher-info.service';

@Component({
  templateUrl: './voucher-info-delete-dialog.component.html',
})
export class VoucherInfoDeleteDialogComponent {
  voucherInfo?: IVoucherInfo;

  constructor(
    protected voucherInfoService: VoucherInfoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.voucherInfoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('voucherInfoListModification');
      this.activeModal.close();
    });
  }
}
