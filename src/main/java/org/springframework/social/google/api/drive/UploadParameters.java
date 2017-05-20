/**
 * Copyright 2011-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.google.api.drive;

/**
 * Builder for parameters to be used in file upload
 * @author Gabriel Axel
 *
 */
public class UploadParameters {
  private boolean convert;
  private boolean ocr;
  private String ocrLanguage;
  private boolean pinned;
  private String sourceLanguage;
  private String targetLanguage;
  private String timedTextLanguage;
  private String timedTextTrackName;

  private void append(final StringBuilder sb, final String parameter, final String value) {
    if (value != null) {
      sb.append('&').append(parameter).append('=').append(value);
    }
  }

  private void append(final StringBuilder sb, final String parameter, final boolean value) {
    if (value) {
      append(sb, parameter, Boolean.toString(value));
    }
  }

  /**
   * Returns a string representation of the true and non-null parameters to
   * be used in the query string
   */
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    append(sb, "convert", convert);
    append(sb, "ocr", ocr);
    append(sb, "ocrLanguage", ocrLanguage);
    append(sb, "pinned", pinned);
    append(sb, "sourceLanguage", sourceLanguage);
    append(sb, "targetLanguage", targetLanguage);
    append(sb, "timedTextLanguage", timedTextLanguage);
    append(sb, "timedTextTrackName", timedTextTrackName);
    return sb.toString();
  }

  /**
   *
   * @param convert Whether to convert this file to the corresponding Google Docs format. (Default: false)
   * @return the {@link UploadParameters} instance
   */
  public UploadParameters setConvert(final boolean convert) {
    this.convert = convert;
    return this;
  }

  /**
   *
   * @param ocr Whether to attempt OCR on .jpg, .png, .gif, or .pdf uploads. (Default: false)
   * @return the {@link UploadParameters} instance
   */
  public UploadParameters setOcr(final boolean ocr) {
    this.ocr = ocr;
    return this;
  }

  /**
   *
   * @param ocrLanguage If ocr is true, hints at the language to use. Valid values are ISO 639-1 codes.
   * @return the {@link UploadParameters} instance
   */
  public UploadParameters setOcrLanguage(final String ocrLanguage) {
    this.ocrLanguage = ocrLanguage;
    return this;
  }

  /**
   *
   * @param pinned Whether to pin the head revision of the uploaded file. (Default: false)
   * @return the {@link UploadParameters} instance
   */
  public UploadParameters setPinned(final boolean pinned) {
    this.pinned = pinned;
    return this;
  }

  /**
   *
   * @param sourceLanguage The language of the original file to be translated.
   * @return the {@link UploadParameters} instance
   */
  public UploadParameters setSourceLanguage(final String sourceLanguage) {
    this.sourceLanguage = sourceLanguage;
    return this;
  }

  /**
   *
   * @param targetLanguage Target language to translate the file to.
   *                      If no sourceLanguage is provided, the API will attempt to detect the language.
   * @return the {@link UploadParameters} instance
   */
  public UploadParameters setTargetLanguage(final String targetLanguage) {
    this.targetLanguage = targetLanguage;
    return this;
  }

  /**
   *
   * @param timedTextLanguage The language of the timed text.
   * @return the {@link UploadParameters} instance
   */
  public UploadParameters setTimedTextLanguage(final String timedTextLanguage) {
    this.timedTextLanguage = timedTextLanguage;
    return this;
  }

  /**
   *
   * @param timedTextTrackName The timed text track name.
   * @return the {@link UploadParameters} instance
   */
  public UploadParameters setTimedTextTrackName(final String timedTextTrackName) {
    this.timedTextTrackName = timedTextTrackName;
    return this;
  }
}
