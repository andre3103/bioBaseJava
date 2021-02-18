import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGerenciaId } from 'app/shared/model/gerencia-id.model';

@Component({
  selector: 'jhi-gerencia-id-detail',
  templateUrl: './gerencia-id-detail.component.html',
})
export class GerenciaIdDetailComponent implements OnInit {
  gerenciaId: IGerenciaId | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ gerenciaId }) => (this.gerenciaId = gerenciaId));
  }

  previousState(): void {
    window.history.back();
  }
}
