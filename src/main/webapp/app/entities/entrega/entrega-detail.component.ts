import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEntrega } from 'app/shared/model/entrega.model';

@Component({
  selector: 'jhi-entrega-detail',
  templateUrl: './entrega-detail.component.html',
})
export class EntregaDetailComponent implements OnInit {
  entrega: IEntrega | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ entrega }) => (this.entrega = entrega));
  }

  previousState(): void {
    window.history.back();
  }
}
