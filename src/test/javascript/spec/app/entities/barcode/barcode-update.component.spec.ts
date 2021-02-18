import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BioBaseApplicationTestModule } from '../../../test.module';
import { BarcodeUpdateComponent } from 'app/entities/barcode/barcode-update.component';
import { BarcodeService } from 'app/entities/barcode/barcode.service';
import { Barcode } from 'app/shared/model/barcode.model';

describe('Component Tests', () => {
  describe('Barcode Management Update Component', () => {
    let comp: BarcodeUpdateComponent;
    let fixture: ComponentFixture<BarcodeUpdateComponent>;
    let service: BarcodeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BioBaseApplicationTestModule],
        declarations: [BarcodeUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(BarcodeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BarcodeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BarcodeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Barcode(123);
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
        const entity = new Barcode();
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
