import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IBarcode } from 'app/shared/model/barcode.model';
import { BarcodeService } from './barcode.service';
import { BarcodeDeleteDialogComponent } from './barcode-delete-dialog.component';

@Component({
  selector: 'jhi-barcode',
  templateUrl: './barcode.component.html',
})
export class BarcodeComponent implements OnInit, OnDestroy {
  barcodes?: IBarcode[];
  eventSubscriber?: Subscription;

  constructor(protected barcodeService: BarcodeService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.barcodeService.query().subscribe((res: HttpResponse<IBarcode[]>) => (this.barcodes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInBarcodes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IBarcode): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInBarcodes(): void {
    this.eventSubscriber = this.eventManager.subscribe('barcodeListModification', () => this.loadAll());
  }

  delete(barcode: IBarcode): void {
    const modalRef = this.modalService.open(BarcodeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.barcode = barcode;
  }
}
