import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITaxonomy } from 'app/shared/model/taxonomy.model';
import { TaxonomyService } from './taxonomy.service';

@Component({
  templateUrl: './taxonomy-delete-dialog.component.html',
})
export class TaxonomyDeleteDialogComponent {
  taxonomy?: ITaxonomy;

  constructor(protected taxonomyService: TaxonomyService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.taxonomyService.delete(id).subscribe(() => {
      this.eventManager.broadcast('taxonomyListModification');
      this.activeModal.close();
    });
  }
}
