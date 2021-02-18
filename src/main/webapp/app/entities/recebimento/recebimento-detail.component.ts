import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRecebimento } from 'app/shared/model/recebimento.model';

@Component({
  selector: 'jhi-recebimento-detail',
  templateUrl: './recebimento-detail.component.html',
})
export class RecebimentoDetailComponent implements OnInit {
  recebimento: IRecebimento | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ recebimento }) => (this.recebimento = recebimento));
  }

  previousState(): void {
    window.history.back();
  }
}
