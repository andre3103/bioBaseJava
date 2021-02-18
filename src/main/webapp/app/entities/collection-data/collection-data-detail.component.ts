import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICollectionData } from 'app/shared/model/collection-data.model';

@Component({
  selector: 'jhi-collection-data-detail',
  templateUrl: './collection-data-detail.component.html',
})
export class CollectionDataDetailComponent implements OnInit {
  collectionData: ICollectionData | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ collectionData }) => (this.collectionData = collectionData));
  }

  previousState(): void {
    window.history.back();
  }
}
