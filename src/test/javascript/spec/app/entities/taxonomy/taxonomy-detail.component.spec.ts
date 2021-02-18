import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BioBaseApplicationTestModule } from '../../../test.module';
import { TaxonomyDetailComponent } from 'app/entities/taxonomy/taxonomy-detail.component';
import { Taxonomy } from 'app/shared/model/taxonomy.model';

describe('Component Tests', () => {
  describe('Taxonomy Management Detail Component', () => {
    let comp: TaxonomyDetailComponent;
    let fixture: ComponentFixture<TaxonomyDetailComponent>;
    const route = ({ data: of({ taxonomy: new Taxonomy(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BioBaseApplicationTestModule],
        declarations: [TaxonomyDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TaxonomyDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TaxonomyDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load taxonomy on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.taxonomy).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
