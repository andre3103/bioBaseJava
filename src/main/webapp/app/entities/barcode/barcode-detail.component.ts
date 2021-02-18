import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBarcode } from 'app/shared/model/barcode.model';

@Component({
  selector: 'jhi-barcode-detail',
  templateUrl: './barcode-detail.component.html',
})
export class BarcodeDetailComponent implements OnInit {
  barcode: IBarcode | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ barcode }) => (this.barcode = barcode));
  }

  previousState(): void {
    window.history.back();
  }
}
