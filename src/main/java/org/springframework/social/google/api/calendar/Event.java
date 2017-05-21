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
package org.springframework.social.google.api.calendar;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.social.google.api.ApiEntity;
import org.springframework.social.google.api.calendar.impl.DateTimeDeserializer;
import org.springframework.social.google.api.calendar.impl.DateTimeSerializer;
import org.springframework.social.google.api.calendar.impl.TimeZoneDeserializer;
import org.springframework.social.google.api.calendar.impl.TimeZoneSerializer;

/**
 * Model class representing an event within Google Calendar.
 * <p>See the reference documentation at
 * <a href="https://developers.google.com/google-apps/calendar/v3/reference/events#resource">
 * https://developers.google.com/google-apps/calendar/v3/reference/events#resource</a>.
 * </p>
 *
 * @author Martin Wink
 */
public class Event extends ApiEntity {
  @JsonInclude(value = Include.NON_NULL)
  private EventStatus status;

  @JsonInclude(value = Include.NON_NULL)
  private String htmlLink;

  @JsonInclude(value = Include.NON_NULL)
  @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private Date created;

  @JsonInclude(value = Include.NON_NULL)
  @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private Date updated;

  @JsonInclude(value = Include.NON_NULL)
  private String summary;

  @JsonInclude(value = Include.NON_NULL)
  private String description;

  @JsonInclude(value = Include.NON_NULL)
  private String location;

  @JsonInclude(value = Include.NON_NULL)
  private String colorId;

  @JsonInclude(value = Include.NON_NULL)
  private Creator creator;

  @JsonInclude(value = Include.NON_NULL)
  private Organizer organizer;

  @JsonInclude(value = Include.NON_NULL)
  private DateTimeTimezone start;

  @JsonInclude(value = Include.NON_NULL)
  private DateTimeTimezone end;

  @JsonInclude(value = Include.NON_NULL)
  private Boolean endTimeUnspecified;

  @JsonInclude(value = Include.NON_NULL)
  private List<String> recurrence;

  @JsonInclude(value = Include.NON_NULL)
  private String recurringEventId;

  @JsonInclude(value = Include.NON_NULL)
  private DateTimeTimezone originalStartTime;

  @JsonInclude(value = Include.NON_NULL)
  private Transparency transparency;

  @JsonInclude(value = Include.NON_NULL)
  private Visibility visibility;

  @JsonProperty("iCalUID")
  @JsonInclude(value = Include.NON_NULL)
  private String iCalUid;

  @JsonInclude(value = Include.NON_NULL)
  private Integer sequence;

  @JsonInclude(value = Include.NON_NULL)
  private List<Attendee> attendees;

  @JsonInclude(value = Include.NON_NULL)
  private Boolean attendeesOmitted;

  @JsonInclude(value = Include.NON_NULL)
  private ExtendedProperties extendedProperties;

  @JsonInclude(value = Include.NON_NULL)
  private String hangoutLink;

  @JsonInclude(value = Include.NON_NULL)
  private Gadget gadget;

  @JsonInclude(value = Include.NON_NULL)
  private Boolean anyoneCanAddSelf;

  @JsonInclude(value = Include.NON_NULL)
  private Boolean guestsCanInviteOthers;

  @JsonInclude(value = Include.NON_NULL)
  private Boolean guestsCanModify;

  @JsonInclude(value = Include.NON_NULL)
  private Boolean guestsCanSeeOtherGuests;

  @JsonInclude(value = Include.NON_NULL)
  private Boolean privateCopy;

  @JsonInclude(value = Include.NON_NULL)
  private Boolean locked;

  @JsonInclude(value = Include.NON_NULL)
  private Reminders reminders;

  @JsonInclude(value = Include.NON_NULL)
  private Source source;

  /**
   * Constructor protected so instances must be retrieved from Google Calendar
   * itself.
   * Only some of the possible modifications are currently implemented.
   */
  protected Event() {
    super();

    presetBooleansThatDefaultToTrue();
  }

  private void presetBooleansThatDefaultToTrue() {
    guestsCanInviteOthers = true;
    guestsCanSeeOtherGuests = true;
  }

  /**
   * The status of this event.
   *
   * @return the value or {@code null} if none.
   */
  public EventStatus getStatus() {
    return status;
  }

  /**
   * Sets the status of this event.
   *
   * @param status the new status, or {@code null} for none.
   * @return this event, for chaining.
   */
  public Event setStatus(final EventStatus status) {
    this.status = status;
    return this;
  }

  /**
   * An absolute link to this event in the Google Calendar Web UI.
   *
   * @return the value or {@code null} if none.
   */
  public String getHtmlLink() {
    return htmlLink;
  }

  /**
   * The creation time of this event.
   *
   * @return the value or {@code null} if none.
   */
  public Date getCreated() {
    return created;
  }

  /**
   * The last modification time of this event.
   *
   * @return the value or {@code null} if none.
   */
  public Date getUpdated() {
    return updated;
  }

  /**
   * The title of this event.
   *
   * @return the value or {@code null} if none.
   */
  public String getSummary() {
    return summary;
  }

  /**
   * Sets the title of this event.
   *
   * @param summary the new title.
   * @return this event, to allow chaining.
   */
  public Event setSummary(final String summary) {
    this.summary = summary;
    return this;
  }

  /**
   * The description of this event.
   *
   * @return the value or {@code null} if none.
   */
  public String getDescription() {
    return description;
  }

  /**
   * The geographic location of this event as free-form text.
   *
   * @return the value or {@code null} if none.
   */
  public String getLocation() {
    return location;
  }

  /**
   * Sets the location of this event.
   *
   * @param location the new location, or {@code null} for none.
   * @return this event, to allow chaining.
   */
  public Event setLocation(final String location) {
    this.location = location;
    return this;
  }

  /**
   * The colour of this event.
   *
   * @return the value or {@code null} if none.
   */
  public String getColorId() {
    return colorId;
  }

  /**
   * The creator of this event.
   *
   * @return the value or {@code null} if none.
   */
  public Creator getCreator() {
    return creator;
  }

  /**
   * The organizer of this event.
   * If the organizer is also an attendee, this is indicated with a separate entry in
   * attendees with the organizer field set to True.
   *
   * @return the value or {@code null} if none.
   */
  public Organizer getOrganizer() {
    return organizer;
  }

  /**
   * The (inclusive) start time of this event.
   * For a recurring event, this is the start time of the first instance.
   *
   * @return the value or {@code null} if none.
   */
  public DateTimeTimezone getStart() {
    return start;
  }

  /**
   * The (exclusive) end time of this event.
   * For a recurring event, this is the end time of the first instance.
   *
   * @return the value or {@code null} if none.
   */
  public DateTimeTimezone getEnd() {
    return end;
  }

  /**
   * Whether the end time is actually unspecified.
   * An end time is still provided for compatibility reasons, even if this attribute is
   * set to {@code true}.
   * If {@code true}, the end time should be disregarded.
   *
   * @return the value or {@code null} if none.
   */
  public Boolean isEndTimeUnspecified() {
    return endTimeUnspecified;
  }

  /**
   * For an instance of a recurring event, return this event ID of the recurring event
   * itself.
   *
   * @return the value or {@code null} if none.
   */
  public String getRecurringEventId() {
    return recurringEventId;
  }

  /**
   * For an instance of a recurring event, return the time at which this event
   * would start according to the recurrence data in the recurring event identified
   * by {@link #recurringEventId}.
   *
   * @return the value or {@code null} if none.
   */
  public DateTimeTimezone getOriginalStartTime() {
    return originalStartTime;
  }

  /**
   * Whether this event blocks time on the calendar.
   *
   * @return the value or {@code null} if none.
   */
  public Transparency getTransparency() {
    return transparency;
  }

  /**
   * The visibility of this event.
   *
   * @return the value or {@code null} if none.
   */
  public Visibility getVisibility() {
    return visibility;
  }

  /**
   * This event's ID in the iCalendar format.
   *
   * @return the value or {@code null} if none.
   */
  public String getiCalUid() {
    return iCalUid;
  }

  /**
   * The sequence number as per iCalendar.
   *
   * @return the value or {@code null} if none.
   */
  public Integer getSequence() {
    return sequence;
  }

  /**
   * Whether attendees may have been omitted from this event's representation.
   * When retrieving an event, this may be due to a restriction specified by the
   * maxAttendee query parameter.
   *
   * @return the value or {@code null} if none.
   */
  public Boolean isAttendeesOmitted() {
    return attendeesOmitted;
  }

  /**
   * An absolute link to the Google+ hangout associated with this event.
   *
   * @return the value or {@code null} if none.
   */
  public String getHangoutLink() {
    return hangoutLink;
  }

  /**
   * Whether anyone can invite themselves to this event.
   *
   * @return the value or {@code null} if none.
   */
  public Boolean isAnyoneCanAddSelf() {
    return anyoneCanAddSelf;
  }

  /**
   * Whether attendees other than the organizer can invite others to this event.
   *
   * @return the value or {@code null} if none.
   */
  public Boolean isGuestsCanInviteOthers() {
    return guestsCanInviteOthers;
  }

  /**
   * Whether attendees other than the organizer can modify this event.
   *
   * @return the value or {@code null} if none.
   */
  public Boolean isGuestsCanModify() {
    return guestsCanModify;
  }

  /**
   * Whether attendees other than the organizer can see who this event's
   * attendees are.
   *
   * @return the value or {@code null} if none.
   */
  public Boolean isGuestsCanSeeOtherGuests() {
    return guestsCanSeeOtherGuests;
  }

  /**
   * Whether this is a private event copy where changes are not shared with
   * other copies on other calendars.
   *
   * @return the value or {@code null} if none.
   */
  public Boolean isPrivateCopy() {
    return privateCopy;
  }

  /**
   * Whether this is a locked event copy where no changes can be made to the
   * main event fields "summary", "description", "location", "start", "end" or "recurrence".
   *
   * @return the value or {@code null} if none.
   */
  public Boolean isLocked() {
    return locked;
  }

  /**
   * A list of RRULE, EXRULE, RDATE and EXDATE lines for a recurring event.
   * This field is omitted for single events or <em>instances</em> of recurring events.
   *
   * @return the value or {@code null} if none.
   */
  public List<String> getRecurrence() {
    return recurrence;
  }

  /**
   * Replaces the list of RRULE, EXRULE, RDATE and EXDATE lines for a recurring event.
   *
   * @param list the new list, or {@code null} for none.
   * @return this event, to allow chaining.
   */
  public Event setRecurrence(final List<String> list) {
    this.recurrence = list;
    return this;
  }

  /**
   * The attendees of this event.
   *
   * @return the value or {@code null} if none.
   */
  public List<Attendee> getAttendees() {
    return attendees;
  }

  /**
   * The extended properties of this event.
   *
   * @return the value or {@code null} if none.
   */
  public ExtendedProperties getExtendedProperties() {
    return extendedProperties;
  }

  /**
   * A gadget that extends this event.
   *
   * @return the value or {@code null} if none.
   */
  public Gadget getGadget() {
    return gadget;
  }

  /**
   * Information about this event's reminders for the authenticated user.
   *
   * @return the value or {@code null} if none.
   */
  public Reminders getReminders() {
    return reminders;
  }

  /**
   * The source of an event from which this event was created; for example a
   * web page, an email message or any document identifiable by an URL using HTTP/HTTPS
   * protocol.
   * Accessible only by the creator of this event.
   *
   * @return the value or {@code null} if none.
   */
  public Source getSource() {
    return source;
  }

  /**
   * Sets whether attendees other than the organizer can invite others to the event.
   *
   * @param guestsCanInviteOthers the new value.
   * @return this event, to allow chaining.
   */
  public Event setGuestsCanInviteOthers(final Boolean guestsCanInviteOthers) {
    this.guestsCanInviteOthers = guestsCanInviteOthers;
    return this;
  }

  /**
   * Sets whether attendees other than the organizer can see who the event's attendees
   * are.
   *
   * @param guestsCanSeeOtherGuests the new value.
   * @return this event, to allow chaining.
   */
  public Event setGuestsCanSeeOtherGuests(final Boolean guestsCanSeeOtherGuests) {
    this.guestsCanSeeOtherGuests = guestsCanSeeOtherGuests;
    return this;
  }

  /**
   * Information about the creator of an event.
   */
  public static class Creator {
    @JsonInclude(value = Include.NON_NULL)
    private String id;

    @JsonInclude(value = Include.NON_NULL)
    private String email;

    @JsonInclude(value = Include.NON_NULL)
    private String displayName;

    @JsonInclude(value = Include.NON_NULL)
    private Boolean self;

    /**
     * The creator's profile ID, if available.
     *
     * @return the value or {@code null} if none.
     */
    public String getId() {
      return id;
    }

    /**
     * The creator's email address, if available.
     *
     * @return the value or {@code null} if none.
     */
    public String getEmail() {
      return email;
    }

    /**
     * The creator's name, if available.
     *
     * @return the value or {@code null} if none.
     */
    public String getDisplayName() {
      return displayName;
    }

    /**
     * Whether the creator corresponds to the calendar on which this copy of
     * the event appears.
     *
     * @return the value or {@code null} if none.
     */
    public Boolean isSelf() {
      return self;
    }
  }

  /**
   * Information about an event's organizer.
   */
  public static class Organizer {
    @JsonInclude(value = Include.NON_NULL)
    private String id;

    @JsonInclude(value = Include.NON_NULL)
    private String email;

    @JsonInclude(value = Include.NON_NULL)
    private String displayName;

    @JsonInclude(value = Include.NON_NULL)
    private Boolean self;

    /**
     * The organizer's profile ID, if available.
     *
     * @return the value or {@code null} if none.
     */
    public String getId() {
      return id;
    }

    /**
     * The organizer's email address, if available.
     *
     * @return the value or {@code null} if none.
     */
    public String getEmail() {
      return email;
    }

    /**
     * The organizer's name, if available.
     *
     * @return the value or {@code null} if none.
     */
    public String getDisplayName() {
      return displayName;
    }

    /**
     * Whether the organizer corresponds to the calendar on which this copy
     * of the event appears.
     *
     * @return the value or {@code null} if none.
     */
    public Boolean isSelf() {
      return self;
    }
  }

  /**
   * Details of the start or end of an event.
   * <p>Only a {@link #date} or a {@link #dateTime} may be specified.</p>
   */
  public static class DateTimeTimezone {
    @JsonInclude(value = Include.NON_NULL)
    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date date;

    @JsonInclude(value = Include.NON_NULL)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    @JsonSerialize(using = DateTimeSerializer.class)
    private Date dateTime;

    @JsonInclude(value = Include.NON_NULL)
    @JsonDeserialize(using = TimeZoneDeserializer.class)
    @JsonSerialize(using = TimeZoneSerializer.class)
    private TimeZone timeZone;

    /**
     * The date, if this is an all-day event.
     * The time should be midnight on the given date.
     *
     * @return the value or {@code null} if none.
     */
    public Date getDate() {
      return date;
    }

    /**
     * Set the date to make this an all-day event.
     * For consistency, if a non {@code null} value is set, making this an all-day
     * event, the time property must be set to {@code null}.
     *
     * @param date the new value or {@code null} if none.
     * @return this {@link DateTimeTimezone}, for chaining.
     */
    public DateTimeTimezone setDate(final Date date) {
      this.date = date;
      return this;
    }

    /**
     * The time, if this is not an all-day event.
     *
     * @return the value or {@code null} if none.
     */
    public Date getDateTime() {
      return dateTime;
    }

    /**
     * Set the time, to make this not an all-day event.
     * For consistency, if a non {@code null} value is set, this cannot also be an
     * all-day event, so the date property must be set to {@code null}.
     *
     * @param dateTime the new value or {@code null} if none.
     * @return this {@link DateTimeTimezone}, for chaining.
     */
    public DateTimeTimezone setDateTime(final Date dateTime) {
      this.dateTime = dateTime;
      return this;
    }

    /**
     * The time zone in which the time is specified (e.g. "Europe/Zurich").
     * The time zone is required for recurring events.
     *
     * @return the value or {@code null} if none.
     */
    public TimeZone getTimeZone() {
      return timeZone;
    }

    /**
     * Set the time zone in which the time is specified (e.g. "Europe/Zurich").
     * The time zone is required for recurring events.
     *
     * @param timeZone the new value or {@code null} if none.
     * @return this {@link DateTimeTimezone}, for chaining.
     */
    public DateTimeTimezone setTimeZone(final TimeZone timeZone) {
      this.timeZone = timeZone;
      return this;
    }
  }

  /**
   * Details of an attendee of an event.
   */
  public static class Attendee {
    @JsonInclude(value = Include.NON_NULL)
    private String id;

    @JsonInclude(value = Include.NON_NULL)
    private String email;

    @JsonInclude(value = Include.NON_NULL)
    private String displayName;

    @JsonInclude(value = Include.NON_NULL)
    private Boolean organizer;

    @JsonInclude(value = Include.NON_NULL)
    private Boolean self;

    @JsonInclude(value = Include.NON_NULL)
    private Boolean resource;

    @JsonInclude(value = Include.NON_NULL)
    private Boolean optional;

    @JsonInclude(value = Include.NON_NULL)
    private AttendeeStatus responseStatus;

    @JsonInclude(value = Include.NON_NULL)
    private String comment;

    @JsonInclude(value = Include.NON_NULL)
    private Integer additionalGuests;

    /**
     * The attendee's Profile ID, if available.
     *
     * @return the value or {@code null} if none.
     */
    public String getId() {
      return id;
    }

    /**
     * The attendee's email address, if available.
     *
     * @return the value or {@code null} if none.
     */
    public String getEmail() {
      return email;
    }

    /**
     * The attendee's name, if available.
     *
     * @return the value or {@code null} if none.
     */
    public String getDisplayName() {
      return displayName;
    }

    /**
     * Whether the attendee is the organizer of the event.
     *
     * @return the value or {@code null} if none.
     */
    public Boolean isOrganizer() {
      return organizer;
    }

    /**
     * Whether this entry represents the calendar on which this copy of the
     * event appears.
     *
     * @return the value or {@code null} if none.
     */
    public Boolean isSelf() {
      return self;
    }

    /**
     * Whether the attendee is a resource.
     *
     * @return the value or {@code null} if none.
     */
    public Boolean isResource() {
      return resource;
    }

    /**
     * Whether this is an optional attendee.
     *
     * @return the value or {@code null} if none.
     */
    public Boolean isOptional() {
      return optional;
    }

    /**
     * The attendee's response status.
     *
     * @return the value or {@code null} if none.
     */
    public AttendeeStatus getResponseStatus() {
      return responseStatus;
    }

    /**
     * The attendee's response comment.
     *
     * @return the value or {@code null} if none.
     */
    public String getComment() {
      return comment;
    }

    /**
     * The number of additional guests.
     *
     * @return the value or {@code null} if none.
     */
    public Integer getAdditionalGuests() {
      return additionalGuests;
    }
  }

  /**
   * Extended properties of an event.
   */
  public static class ExtendedProperties {
    @JsonInclude(value = Include.NON_NULL)
    @JsonProperty("private")
    private Map<String, String> privateProperties;

    @JsonInclude(value = Include.NON_NULL)
    @JsonProperty("shared")
    private Map<String, String> sharedProperties;

    /**
     * Properties that are private to the copy of the event that appears on
     * this calendar.
     *
     * @return the value or {@code null} if none.
     */
    public Map<String, String> getPrivateProperties() {
      return privateProperties;
    }

    /**
     * Properties that are shared between copies of the event on other
     * attendees' calendars.
     *
     * @return the value or {@code null} if none.
     */
    public Map<String, String> getSharedProperties() {
      return sharedProperties;
    }
  }

  /**
   * A gadget that extends an event.
   */
  public static class Gadget {
    @JsonInclude(value = Include.NON_NULL)
    private String type;

    @JsonInclude(value = Include.NON_NULL)
    private String title;

    @JsonInclude(value = Include.NON_NULL)
    private String link;

    @JsonInclude(value = Include.NON_NULL)
    private String iconLink;

    @JsonInclude(value = Include.NON_NULL)
    private Integer width;

    @JsonInclude(value = Include.NON_NULL)
    private Integer height;

    @JsonInclude(value = Include.NON_NULL)
    private DisplayMode display;

    @JsonInclude(value = Include.NON_NULL)
    private Map<String, String> preferences;

    /**
     * The gadget's type.
     *
     * @return the value or {@code null} if none.
     */
    public String getType() {
      return type;
    }

    /**
     * The gadget's title.
     *
     * @return the value or {@code null} if none.
     */
    public String getTitle() {
      return title;
    }

    /**
     * The gadget's URL.
     *
     * @return the value or {@code null} if none.
     */
    public String getLink() {
      return link;
    }

    /**
     * The gadget's icon URL.
     *
     * @return the value or {@code null} if none.
     */
    public String getIconLink() {
      return iconLink;
    }

    /**
     * The gadget's width in pixels.
     *
     * @return the value or {@code null} if none.
     */
    public Integer getWidth() {
      return width;
    }

    /**
     * The gadget's height in pixels.
     *
     * @return the value or {@code null} if none.
     */
    public Integer getHeight() {
      return height;
    }

    /**
     * The gadget's display mode.
     *
     * @return the value or {@code null} if none.
     */
    public DisplayMode getDisplay() {
      return display;
    }

    /**
     * The gadget's preferences.
     *
     * @return the value or {@code null} if none.
     */
    public Map<String, String> getPreferences() {
      return preferences;
    }
  }

  /**
   * Details of the overrides to reminders.
   */
  public static class ReminderOverride {
    @JsonInclude(value = Include.NON_NULL)
    private NotificationMethod method;

    @JsonInclude(value = Include.NON_NULL)
    private Integer minutes;

    /**
     * The method used by this reminder.
     *
     * @return the value or {@code null} if none.
     */
    public NotificationMethod getMethod() {
      return method;
    }

    /**
     * The number of minutes before the start of the event when this reminder
     * should trigger.
     *
     * @return the value or {@code null} if none.
     */
    public Integer getMinutes() {
      return minutes;
    }
  }

  /**
   * Information about the event's reminders for the authenticated user.
   */
  public static class Reminders {
    @JsonInclude(value = Include.NON_NULL)
    private Boolean useDefault;

    @JsonInclude(value = Include.NON_NULL)
    private List<ReminderOverride> overrides;

    /**
     * Whether the default reminders of the calendar apply to this event.
     *
     * @return the value or {@code null} if none.
     */
    public Boolean isUseDefault() {
      return useDefault;
    }

    /**
     * If the event doesn't use the default reminders, this returns the list of
     * reminders specific to the event, or, if not set, indicates that no reminders
     * are set for this event.
     *
     * @return the value or {@code null} if none.
     */
    public List<ReminderOverride> getOverrides() {
      return overrides;
    }
  }

  /**
   * Source from which an event was created; for example a web page, an email message
   * or any document identifiable by an URL using HTTP/HTTPS protocol.
   * Accessible only by the creator of the event.
   */
  public static class Source {
    @JsonInclude(value = Include.NON_NULL)
    private String url;

    @JsonInclude(value = Include.NON_NULL)
    private String title;

    /**
     * The URL of the source pointing to a resource.
     * The URL's protocol will be HTTP or HTTPS.
     *
     * @return the value or {@code null} if none.
     */
    public String getUrl() {
      return url;
    }

    /**
     * The title of the source; for example a title of a web page or an email
     * subject.
     *
     * @return the value or {@code null} if none.
     */
    public String getTitle() {
      return title;
    }
  }
}
