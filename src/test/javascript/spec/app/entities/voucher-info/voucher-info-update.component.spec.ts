import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BioBaseApplicationTestModule } from '../../../test.module';
import { VoucherInfoUpdateComponent } from 'app/entities/voucher-info/voucher-info-update.component';
import { VoucherInfoService } from 'app/entities/voucher-info/voucher-info.service';
import { VoucherInfo } from 'app/shared/model/voucher-info.model';

describe('Component Tests', () => {
  describe('VoucherInfo Management Update Component', () => {
    let comp: VoucherInfoUpdateComponent;
    let fixture: ComponentFixture<VoucherInfoUpdateComponent>;
    let service: VoucherInfoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BioBaseApplicationTestModule],
        declarations: [VoucherInfoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(VoucherInfoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(VoucherInfoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(VoucherInfoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new VoucherInfo(123);
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
        const entity = new VoucherInfo();
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
