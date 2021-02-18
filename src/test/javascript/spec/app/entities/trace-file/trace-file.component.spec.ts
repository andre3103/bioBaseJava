import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { BioBaseApplicationTestModule } from '../../../test.module';
import { TraceFileComponent } from 'app/entities/trace-file/trace-file.component';
import { TraceFileService } from 'app/entities/trace-file/trace-file.service';
import { TraceFile } from 'app/shared/model/trace-file.model';

describe('Component Tests', () => {
  describe('TraceFile Management Component', () => {
    let comp: TraceFileComponent;
    let fixture: ComponentFixture<TraceFileComponent>;
    let service: TraceFileService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BioBaseApplicationTestModule],
        declarations: [TraceFileComponent],
      })
        .overrideTemplate(TraceFileComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TraceFileComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TraceFileService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TraceFile(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.traceFiles && comp.traceFiles[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
