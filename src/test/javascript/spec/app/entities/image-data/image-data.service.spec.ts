import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ImageDataService } from 'app/entities/image-data/image-data.service';
import { IImageData, ImageData } from 'app/shared/model/image-data.model';

describe('Service Tests', () => {
  describe('ImageData Service', () => {
    let injector: TestBed;
    let service: ImageDataService;
    let httpMock: HttpTestingController;
    let elemDefault: IImageData;
    let expectedResult: IImageData | IImageData[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ImageDataService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new ImageData(
        0,
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
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a ImageData', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new ImageData()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ImageData', () => {
        const returnedFromService = Object.assign(
          {
            nameImage: 'BBBBBB',
            imageFile: 'BBBBBB',
            originalSpecimen: 'BBBBBB',
            viewMetadata: 'BBBBBB',
            caption: 'BBBBBB',
            measurement: 'BBBBBB',
            measurementType: 'BBBBBB',
            processId: 'BBBBBB',
            licenseHolder: 'BBBBBB',
            license: 'BBBBBB',
            licenseYear: 'BBBBBB',
            licenseInstitution: 'BBBBBB',
            licenseContact: 'BBBBBB',
            photographer: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of ImageData', () => {
        const returnedFromService = Object.assign(
          {
            nameImage: 'BBBBBB',
            imageFile: 'BBBBBB',
            originalSpecimen: 'BBBBBB',
            viewMetadata: 'BBBBBB',
            caption: 'BBBBBB',
            measurement: 'BBBBBB',
            measurementType: 'BBBBBB',
            processId: 'BBBBBB',
            licenseHolder: 'BBBBBB',
            license: 'BBBBBB',
            licenseYear: 'BBBBBB',
            licenseInstitution: 'BBBBBB',
            licenseContact: 'BBBBBB',
            photographer: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a ImageData', () => {
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
