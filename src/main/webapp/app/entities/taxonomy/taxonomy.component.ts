import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITaxonomy } from 'app/shared/model/taxonomy.model';
import { TaxonomyService } from './taxonomy.service';
import { TaxonomyDeleteDialogComponent } from './taxonomy-delete-dialog.component';

@Component({
  selector: 'jhi-taxonomy',
  templateUrl: './taxonomy.component.html',
})
export class TaxonomyComponent implements OnInit, OnDestroy {
  taxonomies?: ITaxonomy[];
  eventSubscriber?: Subscription;

  constructor(protected taxonomyService: TaxonomyService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.taxonomyService.query().subscribe((res: HttpResponse<ITaxonomy[]>) => (this.taxonomies = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTaxonomies();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITaxonomy): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTaxonomies(): void {
    this.eventSubscriber = this.eventManager.subscribe('taxonomyListModification', () => this.loadAll());
  }

  delete(taxonomy: ITaxonomy): void {
    const modalRef = this.modalService.open(TaxonomyDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.taxonomy = taxonomy;
  }
}
