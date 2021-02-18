import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BioBaseApplicationSharedModule } from 'app/shared/shared.module';
import { TaxonomyComponent } from './taxonomy.component';
import { TaxonomyDetailComponent } from './taxonomy-detail.component';
import { TaxonomyUpdateComponent } from './taxonomy-update.component';
import { TaxonomyDeleteDialogComponent } from './taxonomy-delete-dialog.component';
import { taxonomyRoute } from './taxonomy.route';

@NgModule({
  imports: [BioBaseApplicationSharedModule, RouterModule.forChild(taxonomyRoute)],
  declarations: [TaxonomyComponent, TaxonomyDetailComponent, TaxonomyUpdateComponent, TaxonomyDeleteDialogComponent],
  entryComponents: [TaxonomyDeleteDialogComponent],
})
export class BioBaseApplicationTaxonomyModule {}
