import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICollectionData } from 'app/shared/model/collection-data.model';
import { CollectionDataService } from './collection-data.service';

@Component({
  templateUrl: './collection-data-delete-dialog.component.html',
})
export class CollectionDataDeleteDialogComponent {
  collectionData?: ICollectionData;

  constructor(
    protected collectionDataService: CollectionDataService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.collectionDataService.delete(id).subscribe(() => {
      this.eventManager.broadcast('collectionDataListModification');
      this.activeModal.close();
    });
  }
}
