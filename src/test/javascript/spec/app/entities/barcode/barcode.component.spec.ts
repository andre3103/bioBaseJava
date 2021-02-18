import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { BioBaseApplicationTestModule } from '../../../test.module';
import { BarcodeComponent } from 'app/entities/barcode/barcode.component';
import { BarcodeService } from 'app/entities/barcode/barcode.service';
import { Barcode } from 'app/shared/model/barcode.model';

describe('Component Tests', () => {
  describe('Barcode Management Component', () => {
    let comp: BarcodeComponent;
    let fixture: ComponentFixture<BarcodeComponent>;
    let service: BarcodeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BioBaseApplicationTestModule],
        declarations: [BarcodeComponent],
      })
        .overrideTemplate(BarcodeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BarcodeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BarcodeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Barcode(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.barcodes && comp.barcodes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
