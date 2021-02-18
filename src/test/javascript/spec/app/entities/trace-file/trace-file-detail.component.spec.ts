import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BioBaseApplicationTestModule } from '../../../test.module';
import { TraceFileDetailComponent } from 'app/entities/trace-file/trace-file-detail.component';
import { TraceFile } from 'app/shared/model/trace-file.model';

describe('Component Tests', () => {
  describe('TraceFile Management Detail Component', () => {
    let comp: TraceFileDetailComponent;
    let fixture: ComponentFixture<TraceFileDetailComponent>;
    const route = ({ data: of({ traceFile: new TraceFile(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BioBaseApplicationTestModule],
        declarations: [TraceFileDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TraceFileDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TraceFileDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load traceFile on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.traceFile).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
