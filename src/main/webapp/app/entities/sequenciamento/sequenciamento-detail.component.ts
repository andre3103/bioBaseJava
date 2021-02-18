import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISequenciamento } from 'app/shared/model/sequenciamento.model';

@Component({
  selector: 'jhi-sequenciamento-detail',
  templateUrl: './sequenciamento-detail.component.html',
})
export class SequenciamentoDetailComponent implements OnInit {
  sequenciamento: ISequenciamento | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sequenciamento }) => (this.sequenciamento = sequenciamento));
  }

  previousState(): void {
    window.history.back();
  }
}
