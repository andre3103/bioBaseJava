import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BioBaseApplicationTestModule } from '../../../test.module';
import { PlacaDetailComponent } from 'app/entities/placa/placa-detail.component';
import { Placa } from 'app/shared/model/placa.model';

describe('Component Tests', () => {
  describe('Placa Management Detail Component', () => {
    let comp: PlacaDetailComponent;
    let fixture: ComponentFixture<PlacaDetailComponent>;
    const route = ({ data: of({ placa: new Placa(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BioBaseApplicationTestModule],
        declarations: [PlacaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(PlacaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PlacaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load placa on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.placa).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
