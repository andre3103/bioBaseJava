import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BioBaseApplicationTestModule } from '../../../test.module';
import { BarcodeDetailComponent } from 'app/entities/barcode/barcode-detail.component';
import { Barcode } from 'app/shared/model/barcode.model';

describe('Component Tests', () => {
  describe('Barcode Management Detail Component', () => {
    let comp: BarcodeDetailComponent;
    let fixture: ComponentFixture<BarcodeDetailComponent>;
    const route = ({ data: of({ barcode: new Barcode(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BioBaseApplicationTestModule],
        declarations: [BarcodeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BarcodeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BarcodeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load barcode on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.barcode).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
