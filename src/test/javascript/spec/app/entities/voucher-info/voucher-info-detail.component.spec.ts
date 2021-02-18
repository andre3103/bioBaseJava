import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BioBaseApplicationTestModule } from '../../../test.module';
import { VoucherInfoDetailComponent } from 'app/entities/voucher-info/voucher-info-detail.component';
import { VoucherInfo } from 'app/shared/model/voucher-info.model';

describe('Component Tests', () => {
  describe('VoucherInfo Management Detail Component', () => {
    let comp: VoucherInfoDetailComponent;
    let fixture: ComponentFixture<VoucherInfoDetailComponent>;
    const route = ({ data: of({ voucherInfo: new VoucherInfo(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BioBaseApplicationTestModule],
        declarations: [VoucherInfoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(VoucherInfoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(VoucherInfoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load voucherInfo on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.voucherInfo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
