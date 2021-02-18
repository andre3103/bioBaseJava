import { IGerenciaId } from 'app/shared/model/gerencia-id.model';

export interface IImageData {
  id?: number;
  nameImage?: string;
  imageFile?: string;
  originalSpecimen?: string;
  viewMetadata?: string;
  caption?: string;
  measurement?: string;
  measurementType?: string;
  processId?: string;
  licenseHolder?: string;
  license?: string;
  licenseYear?: string;
  licenseInstitution?: string;
  licenseContact?: string;
  photographer?: string;
  idSample?: IGerenciaId;
}

export class ImageData implements IImageData {
  constructor(
    public id?: number,
    public nameImage?: string,
    public imageFile?: string,
    public originalSpecimen?: string,
    public viewMetadata?: string,
    public caption?: string,
    public measurement?: string,
    public measurementType?: string,
    public processId?: string,
    public licenseHolder?: string,
    public license?: string,
    public licenseYear?: string,
    public licenseInstitution?: string,
    public licenseContact?: string,
    public photographer?: string,
    public idSample?: IGerenciaId
  ) {}
}
