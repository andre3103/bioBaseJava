import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITraceFile } from 'app/shared/model/trace-file.model';

@Component({
  selector: 'jhi-trace-file-detail',
  templateUrl: './trace-file-detail.component.html',
})
export class TraceFileDetailComponent implements OnInit {
  traceFile: ITraceFile | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ traceFile }) => (this.traceFile = traceFile));
  }

  previousState(): void {
    window.history.back();
  }
}
