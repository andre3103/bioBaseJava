import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITraceFile } from 'app/shared/model/trace-file.model';
import { TraceFileService } from './trace-file.service';

@Component({
  templateUrl: './trace-file-delete-dialog.component.html',
})
export class TraceFileDeleteDialogComponent {
  traceFile?: ITraceFile;

  constructor(protected traceFileService: TraceFileService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.traceFileService.delete(id).subscribe(() => {
      this.eventManager.broadcast('traceFileListModification');
      this.activeModal.close();
    });
  }
}
