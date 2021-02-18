import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BioBaseApplicationTestModule } from '../../../test.module';
import { EntregaDetailComponent } from 'app/entities/entrega/entrega-detail.component';
import { Entrega } from 'app/shared/model/entrega.model';

describe('Component Tests', () => {
  describe('Entrega Management Detail Component', () => {
    let comp: EntregaDetailComponent;
    let fixture: ComponentFixture<EntregaDetailComponent>;
    const route = ({ data: of({ entrega: new Entrega(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BioBaseApplicationTestModule],
        declarations: [EntregaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(EntregaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EntregaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load entrega on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.entrega).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
