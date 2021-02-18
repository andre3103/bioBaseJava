import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BioBaseApplicationSharedModule } from 'app/shared/shared.module';
import { CollectionDataComponent } from './collection-data.component';
import { CollectionDataDetailComponent } from './collection-data-detail.component';
import { CollectionDataUpdateComponent } from './collection-data-update.component';
import { CollectionDataDeleteDialogComponent } from './collection-data-delete-dialog.component';
import { collectionDataRoute } from './collection-data.route';

@NgModule({
  imports: [BioBaseApplicationSharedModule, RouterModule.forChild(collectionDataRoute)],
  declarations: [
    CollectionDataComponent,
    CollectionDataDetailComponent,
    CollectionDataUpdateComponent,
    CollectionDataDeleteDialogComponent,
  ],
  entryComponents: [CollectionDataDeleteDialogComponent],
})
export class BioBaseApplicationCollectionDataModule {}
