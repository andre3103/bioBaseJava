import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BioBaseApplicationTestModule } from '../../../test.module';
import { RecebimentoDetailComponent } from 'app/entities/recebimento/recebimento-detail.component';
import { Recebimento } from 'app/shared/model/recebimento.model';

describe('Component Tests', () => {
  describe('Recebimento Management Detail Component', () => {
    let comp: RecebimentoDetailComponent;
    let fixture: ComponentFixture<RecebimentoDetailComponent>;
    const route = ({ data: of({ recebimento: new Recebimento(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BioBaseApplicationTestModule],
        declarations: [RecebimentoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(RecebimentoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RecebimentoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load recebimento on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.recebimento).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
