import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BioBaseApplicationTestModule } from '../../../test.module';
import { CollectionDataDetailComponent } from 'app/entities/collection-data/collection-data-detail.component';
import { CollectionData } from 'app/shared/model/collection-data.model';

describe('Component Tests', () => {
  describe('CollectionData Management Detail Component', () => {
    let comp: CollectionDataDetailComponent;
    let fixture: ComponentFixture<CollectionDataDetailComponent>;
    const route = ({ data: of({ collectionData: new CollectionData(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BioBaseApplicationTestModule],
        declarations: [CollectionDataDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CollectionDataDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CollectionDataDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load collectionData on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.collectionData).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
