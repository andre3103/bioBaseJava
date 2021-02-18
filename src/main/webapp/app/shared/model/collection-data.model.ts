import { Moment } from 'moment';
import { IVoucherInfo } from 'app/shared/model/voucher-info.model';

export interface ICollectionData {
  id?: number;
  collector?: string;
  collectionDate?: Moment;
  countryOcean?: string;
  stateProvince?: string;
  region?: string;
  sector?: string;
  exactSite?: string;
  identif?: string;
  latitude?: string;
  longitude?: string;
  elevation?: string;
  depth?: string;
  elevationPrecision?: string;
  depthPrecision?: string;
  gpsSource?: string;
  coordinateAccuracy?: string;
  eventTime?: string;
  collectionDateOccuracy?: string;
  habitat?: string;
  samplingProtocol?: string;
  collectionNotes?: string;
  siteCode?: string;
  collectionEventId?: string;
  voucherInfo?: IVoucherInfo;
}

export class CollectionData implements ICollectionData {
  constructor(
    public id?: number,
    public collector?: string,
    public collectionDate?: Moment,
    public countryOcean?: string,
    public stateProvince?: string,
    public region?: string,
    public sector?: string,
    public exactSite?: string,
    public identif?: string,
    public latitude?: string,
    public longitude?: string,
    public elevation?: string,
    public depth?: string,
    public elevationPrecision?: string,
    public depthPrecision?: string,
    public gpsSource?: string,
    public coordinateAccuracy?: string,
    public eventTime?: string,
    public collectionDateOccuracy?: string,
    public habitat?: string,
    public samplingProtocol?: string,
    public collectionNotes?: string,
    public siteCode?: string,
    public collectionEventId?: string,
    public voucherInfo?: IVoucherInfo
  ) {}
}
