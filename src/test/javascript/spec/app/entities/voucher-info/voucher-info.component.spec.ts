import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { BioBaseApplicationTestModule } from '../../../test.module';
import { VoucherInfoComponent } from 'app/entities/voucher-info/voucher-info.component';
import { VoucherInfoService } from 'app/entities/voucher-info/voucher-info.service';
import { VoucherInfo } from 'app/shared/model/voucher-info.model';

describe('Component Tests', () => {
  describe('VoucherInfo Management Component', () => {
    let comp: VoucherInfoComponent;
    let fixture: ComponentFixture<VoucherInfoComponent>;
    let service: VoucherInfoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BioBaseApplicationTestModule],
        declarations: [VoucherInfoComponent],
      })
        .overrideTemplate(VoucherInfoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(VoucherInfoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(VoucherInfoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new VoucherInfo(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.voucherInfos && comp.voucherInfos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
