import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { CollectionDataService } from 'app/entities/collection-data/collection-data.service';
import { ICollectionData, CollectionData } from 'app/shared/model/collection-data.model';

describe('Service Tests', () => {
  describe('CollectionData Service', () => {
    let injector: TestBed;
    let service: CollectionDataService;
    let httpMock: HttpTestingController;
    let elemDefault: ICollectionData;
    let expectedResult: ICollectionData | ICollectionData[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CollectionDataService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new CollectionData(
        0,
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            collectionDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a CollectionData', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            collectionDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            collectionDate: currentDate,
          },
          returnedFromService
        );

        service.create(new CollectionData()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a CollectionData', () => {
        const returnedFromService = Object.assign(
          {
            collector: 'BBBBBB',
            collectionDate: currentDate.format(DATE_FORMAT),
            countryOcean: 'BBBBBB',
            stateProvince: 'BBBBBB',
            region: 'BBBBBB',
            sector: 'BBBBBB',
            exactSite: 'BBBBBB',
            identif: 'BBBBBB',
            latitude: 'BBBBBB',
            longitude: 'BBBBBB',
            elevation: 'BBBBBB',
            depth: 'BBBBBB',
            elevationPrecision: 'BBBBBB',
            depthPrecision: 'BBBBBB',
            gpsSource: 'BBBBBB',
            coordinateAccuracy: 'BBBBBB',
            eventTime: 'BBBBBB',
            collectionDateOccuracy: 'BBBBBB',
            habitat: 'BBBBBB',
            samplingProtocol: 'BBBBBB',
            collectionNotes: 'BBBBBB',
            siteCode: 'BBBBBB',
            collectionEventId: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            collectionDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of CollectionData', () => {
        const returnedFromService = Object.assign(
          {
            collector: 'BBBBBB',
            collectionDate: currentDate.format(DATE_FORMAT),
            countryOcean: 'BBBBBB',
            stateProvince: 'BBBBBB',
            region: 'BBBBBB',
            sector: 'BBBBBB',
            exactSite: 'BBBBBB',
            identif: 'BBBBBB',
            latitude: 'BBBBBB',
            longitude: 'BBBBBB',
            elevation: 'BBBBBB',
            depth: 'BBBBBB',
            elevationPrecision: 'BBBBBB',
            depthPrecision: 'BBBBBB',
            gpsSource: 'BBBBBB',
            coordinateAccuracy: 'BBBBBB',
            eventTime: 'BBBBBB',
            collectionDateOccuracy: 'BBBBBB',
            habitat: 'BBBBBB',
            samplingProtocol: 'BBBBBB',
            collectionNotes: 'BBBBBB',
            siteCode: 'BBBBBB',
            collectionEventId: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            collectionDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a CollectionData', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
