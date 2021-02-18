import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPlaca } from 'app/shared/model/placa.model';

@Component({
  selector: 'jhi-placa-detail',
  templateUrl: './placa-detail.component.html',
})
export class PlacaDetailComponent implements OnInit {
  placa: IPlaca | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ placa }) => (this.placa = placa));
  }

  previousState(): void {
    window.history.back();
  }
}
