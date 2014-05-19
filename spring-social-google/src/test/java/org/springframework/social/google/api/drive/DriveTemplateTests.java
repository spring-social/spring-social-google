/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.google.api.drive;

import static org.junit.Assert.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withNoContent;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.Test;
import org.springframework.social.google.api.AbstractGoogleApiTest;

public class DriveTemplateTests extends AbstractGoogleApiTest {

	@Test
	public void getAbout() {

		mockServer
				.expect(requestTo("https://www.googleapis.com/drive/v2/about"))
				.andExpect(method(GET))
				.andRespond(
						withSuccess(jsonResource("about"), APPLICATION_JSON));

		DriveAbout about = google.driveOperations().getAbout();

		assertNotNull(about);
		assertEquals("Gabriel Axel", about.getName());

		assertNotNull(about.getUser());
		assertEquals("Gabriel Axel", about.getUser().getDisplayName());
		assertEquals(
				"https://lh5.googleusercontent.com/-UyuMuAWmKIM/AAAAAAAAAAI/AAAAAAAAAn0/pMK2DzFNBNI/s64/photo.jpg",
				about.getUser().getPictureUrl());

		assertEquals(16106127360L, about.getQuotaBytesTotal());
		assertEquals(845326524L, about.getQuotaBytesUsed());
		assertEquals(2400242868L, about.getQuotaBytesUsedAggregate());
		assertEquals(429744524L, about.getQuotaBytesUsedInTrash());

		assertEquals(111488L, about.getLargestChangeId());
		assertEquals("0AHDS_k_gCihOUk9PVA", about.getRootFolderId());
		assertEquals("allowed", about.getDomainSharingPolicy());
		assertEquals("09558676735003532639", about.getPermissionId());

		assertEquals(23, about.getImportFormats().size());
		assertEquals(6, about.getExportFormats().size());
		assertEquals(2, about.getFeatures().size());
		assertEquals(6, about.getMaxUploadSizes().size());

		assertFalse(about.isCurrentAppInstalled());
		assertEquals("en-US", about.getLanguageCode());
	}

	@Test
	public void createFileMetadata() {

		mockServer
				.expect(requestTo("https://www.googleapis.com/drive/v2/files/"))
				.andExpect(method(POST))
				.andRespond(withSuccess(jsonResource("file"), APPLICATION_JSON));

		DriveFile file = google.driveOperations().createFileMetadata(
				DriveFile.builder().setTitle("newfile")
						.setDescription("A New File").build());

		assertNotNull(file);
		assertEquals("0B3DS_k_gCihOVXpISDlScnJpTFk", file.getId());
		assertEquals("newfile", file.getTitle());
		assertEquals("A New File", file.getDescription());
		assertEquals("application/octet-stream", file.getMimeType());
		assertEquals("\"sIP8ArR2PAy9qIx8FYkTpbHmKik/MTQwMDQ4NDIzNzc3OQ\"",
				file.getEtag());
		assertEquals(
				"https://content.googleapis.com/drive/v2/files/0B3DS_k_gCihOVXpISDlScnJpTFk",
				file.getSelfLink());
		assertEquals(
				"https://docs.google.com/file/d/0B3DS_k_gCihOVXpISDlScnJpTFk/edit?usp=drivesdk",
				file.getAlternateLink());
		assertEquals(
				"https://ssl.gstatic.com/docs/doclist/images/icon_10_generic_list.png",
				file.getIconLink());
		assertFalse(file.isStarred());
		assertFalse(file.isHidden());
		assertFalse(file.isTrashed());
		assertFalse(file.isRestricted());
		assertTrue(file.isViewed());
		assertEquals(
				"https://doc-0s-4c-docs.googleusercontent.com/docs/securesc/onj78cqef11u939b14bgc0mu1vntl04c/1mpjiafeocj32omvpv6augb74kl0hge3/1400479200000/09558676735003532639/09558676735003532639/0B3DS_k_gCihOVXpISDlScnJpTFk?h=16653014193614665626&e=download&gd=true",
				file.getDownloadUrl());

		assertNotNull(file.getUserPermission());
		assertEquals("me", file.getUserPermission().getId());
		assertEquals(
				"\"sIP8ArR2PAy9qIx8FYkTpbHmKik/VACuEBTone13WaJiFZKx-FuSPpA\"",
				file.getUserPermission().getEtag());
		assertEquals(PermissionRole.OWNER, file.getUserPermission().getRole());
		assertEquals(PermissionType.USER, file.getUserPermission().getType());

	}

	@Test
	public void deleteFile() {

		mockServer
				.expect(requestTo("https://www.googleapis.com/drive/v2/files/0B3DS_k_gCihOVXpISDlScnJpTFk"))
				.andExpect(method(DELETE)).andRespond(withNoContent());
		google.driveOperations().delete("0B3DS_k_gCihOVXpISDlScnJpTFk");
	}

}
