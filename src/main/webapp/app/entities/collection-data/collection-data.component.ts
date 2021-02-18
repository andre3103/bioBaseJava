import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICollectionData } from 'app/shared/model/collection-data.model';
import { CollectionDataService } from './collection-data.service';
import { CollectionDataDeleteDialogComponent } from './collection-data-delete-dialog.component';

@Component({
  selector: 'jhi-collection-data',
  templateUrl: './collection-data.component.html',
})
export class CollectionDataComponent implements OnInit, OnDestroy {
  collectionData?: ICollectionData[];
  eventSubscriber?: Subscription;

  constructor(
    protected collectionDataService: CollectionDataService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.collectionDataService.query().subscribe((res: HttpResponse<ICollectionData[]>) => (this.collectionData = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCollectionData();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICollectionData): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCollectionData(): void {
    this.eventSubscriber = this.eventManager.subscribe('collectionDataListModification', () => this.loadAll());
  }

  delete(collectionData: ICollectionData): void {
    const modalRef = this.modalService.open(CollectionDataDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.collectionData = collectionData;
  }
}
