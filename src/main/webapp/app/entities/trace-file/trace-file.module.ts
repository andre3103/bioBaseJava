import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BioBaseApplicationSharedModule } from 'app/shared/shared.module';
import { TraceFileComponent } from './trace-file.component';
import { TraceFileDetailComponent } from './trace-file-detail.component';
import { TraceFileUpdateComponent } from './trace-file-update.component';
import { TraceFileDeleteDialogComponent } from './trace-file-delete-dialog.component';
import { traceFileRoute } from './trace-file.route';

@NgModule({
  imports: [BioBaseApplicationSharedModule, RouterModule.forChild(traceFileRoute)],
  declarations: [TraceFileComponent, TraceFileDetailComponent, TraceFileUpdateComponent, TraceFileDeleteDialogComponent],
  entryComponents: [TraceFileDeleteDialogComponent],
})
export class BioBaseApplicationTraceFileModule {}
