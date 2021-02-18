import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BioBaseApplicationTestModule } from '../../../test.module';
import { TraceFileUpdateComponent } from 'app/entities/trace-file/trace-file-update.component';
import { TraceFileService } from 'app/entities/trace-file/trace-file.service';
import { TraceFile } from 'app/shared/model/trace-file.model';

describe('Component Tests', () => {
  describe('TraceFile Management Update Component', () => {
    let comp: TraceFileUpdateComponent;
    let fixture: ComponentFixture<TraceFileUpdateComponent>;
    let service: TraceFileService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BioBaseApplicationTestModule],
        declarations: [TraceFileUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TraceFileUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TraceFileUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TraceFileService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TraceFile(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new TraceFile();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
