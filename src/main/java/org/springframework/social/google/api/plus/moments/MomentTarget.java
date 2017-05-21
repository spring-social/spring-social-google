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
package org.springframework.social.google.api.plus.moments;

import java.util.Date;
import java.util.List;

/**
 * A moment's target. Construct with a URL of a resource containing metadata as
 * required for the specific activity type. When fetching, the fields will
 * contain the metadata values from that resource.
 *
 * @author Gabriel Axel
 *
 */
public class MomentTarget {

  private String url;

  private String id;

  private String name;

  private String description;

  private String image;

  private List<Author> author;

  private String text;

  private Date datePublished;

  private String duration;

  private Album inAlbum;

  private Audio audio;

  private Artist byArtist;

  protected MomentTarget() {
  }

  public MomentTarget(final String url) {
    this.url = url;
  }

  public String getUrl() {
    return url;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getImage() {
    return image;
  }

  public List<Author> getAuthor() {
    return author;
  }

  public String getText() {
    return text;
  }

  public Date getDatePublished() {
    return datePublished;
  }

  public String getDuration() {
    return duration;
  }

  public Album getInAlbum() {
    return inAlbum;
  }

  public Audio getAudio() {
    return audio;
  }

  public Artist getByArtist() {
    return byArtist;
  }
}
