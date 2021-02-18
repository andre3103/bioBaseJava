import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITraceFile } from 'app/shared/model/trace-file.model';
import { TraceFileService } from './trace-file.service';
import { TraceFileDeleteDialogComponent } from './trace-file-delete-dialog.component';

@Component({
  selector: 'jhi-trace-file',
  templateUrl: './trace-file.component.html',
})
export class TraceFileComponent implements OnInit, OnDestroy {
  traceFiles?: ITraceFile[];
  eventSubscriber?: Subscription;

  constructor(protected traceFileService: TraceFileService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.traceFileService.query().subscribe((res: HttpResponse<ITraceFile[]>) => (this.traceFiles = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTraceFiles();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITraceFile): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTraceFiles(): void {
    this.eventSubscriber = this.eventManager.subscribe('traceFileListModification', () => this.loadAll());
  }

  delete(traceFile: ITraceFile): void {
    const modalRef = this.modalService.open(TraceFileDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.traceFile = traceFile;
  }
}
