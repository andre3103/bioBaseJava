import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITaxonomy } from 'app/shared/model/taxonomy.model';

@Component({
  selector: 'jhi-taxonomy-detail',
  templateUrl: './taxonomy-detail.component.html',
})
export class TaxonomyDetailComponent implements OnInit {
  taxonomy: ITaxonomy | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ taxonomy }) => (this.taxonomy = taxonomy));
  }

  previousState(): void {
    window.history.back();
  }
}
