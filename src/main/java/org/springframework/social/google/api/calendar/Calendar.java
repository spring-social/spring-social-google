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

import java.util.List;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.social.google.api.ApiEntity;
import org.springframework.social.google.api.calendar.impl.TimeZoneDeserializer;
import org.springframework.social.google.api.calendar.impl.TimeZoneSerializer;

/**
 * Model class representing a calendar within Google Calendar.
 * <p>See the reference documentation at
 * <a href="https://developers.google.com/google-apps/calendar/v3/reference/calendarList#resource">
 * https://developers.google.com/google-apps/calendar/v3/reference/calendarList#resource</a>.
 * </p>
 * @author Martin Wink
 */
public class Calendar extends ApiEntity {

  @JsonInclude(value = Include.NON_NULL)
  private String summary;
  @JsonInclude(value = Include.NON_NULL)
  private String description;
  @JsonInclude(value = Include.NON_NULL)
  private String location;
  @JsonInclude(value = Include.NON_NULL)
  @JsonDeserialize(using = TimeZoneDeserializer.class)
  @JsonSerialize(using = TimeZoneSerializer.class)
  private TimeZone timeZone;
  @JsonInclude(value = Include.NON_NULL)
  private String summaryOverride;
  @JsonInclude(value = Include.NON_NULL)
  private String colorId;
  @JsonInclude(value = Include.NON_NULL)
  private String backgroundColor;
  @JsonInclude(value = Include.NON_NULL)
  private String foregroundColor;
  @JsonInclude(value = Include.NON_DEFAULT)
  private boolean hidden;
  @JsonInclude(value = Include.NON_DEFAULT)
  private boolean selected;
  @JsonInclude(value = Include.NON_NULL)
  private PermissionRole accessRole;
  @JsonInclude(value = Include.NON_NULL)
  private List<EventReminder> defaultReminders;
  @JsonInclude(value = Include.NON_NULL)
  private NotificationSettings notificationSettings;
  @JsonInclude(value = Include.NON_DEFAULT)
  private boolean primary;
  @JsonInclude(value = Include.NON_DEFAULT)
  private boolean deleted;

  /**
   * Constructor protected so instances must be retrieved from Google Calendar.
   */
  protected Calendar() {
    super();
  }

  /**
   * The title of this calendar.
   * @return the value or {@code null} if none.
   */
  public String getSummary() {
    return summary;
  }

  /**
   * The description of this calendar.
   * @return the value or {@code null} if none.
   */
  public String getDescription() {
    return description;
  }

  /**
   * The geographic location of this calendar as free-form text.
   * @return the value or {@code null} if none.
   */
  public String getLocation() {
    return location;
  }

  /**
   * The time zone of this calendar.
   * @return the value or {@code null} if none.
   */
  public TimeZone getTimeZone() {
    return timeZone;
  }

  /**
   * The overriding summary that the authenticated user has set for this calendar.
   * @return the value or {@code null} if none.
   */
  public String getSummaryOverride() {
    return summaryOverride;
  }

  /**
   * The colour of this calendar.
   * @return the value or {@code null} if none.
   */
  public String getColorId() {
    return colorId;
  }

  /**
   * The background colour of this calendar in hexadecimal format "#rrggbb".
   * @return the value or {@code null} if none.
   */
  public String getBackgroundColor() {
    return backgroundColor;
  }

  /**
   * The foreground colour of this calendar in hexadecimal format "#rrggbb".
   * @return the value or {@code null} if none.
   */
  public String getForegroundColor() {
    return foregroundColor;
  }

  /**
   * Whether this calendar has been hidden from the list.
   * @return the value.
   */
  public boolean isHidden() {
    return hidden;
  }

  /**
   * Whether this calendar's content is displayed in the calendar UI.
   * @return the value.
   */
  public boolean isSelected() {
    return selected;
  }

  /**
   * The effective access role that the authenticated user has on this calendar.
   * @return the value or {@code null} if none.
   */
  public PermissionRole getAccessRole() {
    return accessRole;
  }

  /**
   * The default reminders that the authenticated user has for this calendar.
   * @return the value or {@code null} if none.
   */
  public List<EventReminder> getDefaultReminders() {
    return defaultReminders;
  }

  /**
   * The notifications that the authenticated user is receiving for this calendar.
   * @return the value or {@code null} if none.
   */
  public NotificationSettings getNotificationSettings() {
    return notificationSettings;
  }

  /**
   * Whether this calendar is the primary calendar of the authenticated user.
   * @return the value.
   */
  public boolean isPrimary() {
    return primary;
  }

  /**
   * Whether this calendar has been deleted from the calendar list.
   * @return the value.
   */
  public boolean isDeleted() {
    return deleted;
  }

  /**
   * Details of event reminders.
   */
  public static class EventReminder {
    private NotificationMethod method;
    private int minutes;

    /**
     * The notification method used by the reminder.
     * @return the value.
     */
    public NotificationMethod getMethod() {
      return method;
    }

    /**
     * The number of minutes before the start of the event when the reminder should
     * trigger.
     * @return the value.
     */
    public int getMinutes() {
      return minutes;
    }
  }

  /**
   * Details of an individual notification.
   */
  public static class Notification {
    private NotificationType type;
    private NotificationMethod method;

    /**
     * The type of notification.
     * @return the value.
     */
    public NotificationType getType() {
      return type;
    }

    /**
     * The method used to deliver the notification.
     * @return the value.
     */
    public NotificationMethod getMethod() {
      return method;
    }
  }

  /**
   * Details of notifications.
   */
  public static class NotificationSettings {
    private List<Notification> notifications;

    /**
     * The list of notifications set for this calendar.
     * @return the value.
     */
    public List<Notification> getNotifications() {
      return notifications;
    }
  }
}
