package org.unidal.webres.tag.img;

import org.junit.BeforeClass;
import org.junit.Test;

import org.unidal.webres.resource.ResourceConstant;
import org.unidal.webres.resource.expression.ImageExpression;
import org.unidal.webres.resource.expression.ResourceExpressionParser;
import org.unidal.webres.resource.img.ImageFactory;
import org.unidal.webres.tag.support.ResourceTagTestSupport;

public class ImageTagTest extends ResourceTagTestSupport {
   @BeforeClass
   public static void beforeClass() throws Exception {
      setup(new ImageTagTest().copyResourceFrom("/ImageTagTest"));
   }

   @Override
   protected void configure() throws Exception {
      super.configure();

      getRegistry().register(String.class, ResourceConstant.Image.SharedUrlPrefix, "http://res.unidal.org/img");
      getRegistry().register(String.class, ResourceConstant.Image.SharedSecureUrlPrefix, "https://res.unidal.org/img");
   }

   @Override
   protected String getContextPath() {
      return "/test";
   }

   @Test
   public void testAttributesOverride() {
      ImageTag tag = new ImageTag();
      ImageTagModel model = tag.getModel();

      model.setValue(ImageFactory.forRef().createLocalRef("/half/eBayLogo.gif"));
      model.setDynamicAttribute("src", "..."); // this will be ignored
      model.setDynamicAttribute("id", "myimage");
      model.setDynamicAttribute("width", "55");
      model.setDynamicAttribute("height", "90");
      model.setDynamicAttribute("alt", "My Image");

      checkTag(tag,
            "<img src=\"/test/img/half/eBayLogo.gif\" id=\"myimage\" width=\"55\" height=\"90\" alt=\"My Image\">");
   }

   @Test
   public void testDataUri() {
      ImageTag tag = new ImageTag();
      ImageTagModel model = tag.getModel();

      model.setValue(ImageFactory.forRef().createLocalRef("/half/eBayLogo.gif"));
      model.setRenderType(ImageTagRenderType.DATA_URI);

      checkTag(
            tag,
            "<img src=\"data:image/gif;base64,R0lGODlhbgAtAO+/vQAA77+9ACbvv73vv70AAADvv73vv73vv71/77+9AABfX++/ve+/vQDvv73vv70AZmZm77+977+977+977+9MzPvv73vv73vv73vv73vv71/AAAALy/vv73vv71mAO+/ve+/vSPvv73vv73vv73vv73vv71A77+977+977+977+977+9VQBj77+977+977+977+9cwnvv73vv73vv73vv73vv73vv73vv73vv71A77+9X1/vv73vv73vv70I77+9EBDvv70HRO+/ve+/ve+/vT4pa++/ve+/vR/vv73vv73vv73vv73vv70QEO+/ve+/ve+/vQDvv73vv73fiFY877+977+977+977+977+935kAM0BA77+977+977+9b2/vv73vv73vv70A77+977+935/vv73vv73vv73vv71g77+977+9X2g6Xe+/vXBw77+9Z3vvv73vv70g77+9ABkA77+977+977+977+977+977+9dQBP77+9ICDvv71AQO+/ve+/ve+/vSHvv70EAQcAPwAsAAAAAG4ALQAABu+/ve+/ve+/vXBILBrvv73IpFLvv70LOO+/ve+/ve+/vWk577+9Rmfvv70MIu+/ve+/vVDvv73vv73vv71XY++/vWLvv73vv73vv73vv71Yfl3vv718Hm7vv73vv73vv73vv719AO+/ve+/ve+/vX10YiwC77+977+9AglhMBwcDAwtHGR3I0sjdwFCfyEhXn/vv71h77+977+977+977+9SxwyIi/vv73vv70vBgcmEGU3S3pqfH3vv73vv73vv71f77+977+977+9STLvv73vv73vv70mB8WwUe+/vUh2akNaQ3Hvv73vv73vv73vv71K77+977+977+977+977+9B++/vU8aSe+/vWUTRFpcXu+/ve+/vdO+Ri3vv73vv73vv73vv73vv71OU0bvv71W77+957q877+977+9Re+/ve+/vQxCYEhI77+977+977+977+9K0fvv71ZSe+/vU/vv70+Q++/vXDvv70m77+977+9MG0H77+9GDlx77+9XkPvv73vv70QIWEn77+9RREGBDHvv716YmvIjEkfPz3vv712BO+/vTAOJ1Xvv73bpnEIR++/vTXvv71i77+9TO+/vWQR77+977+9OHPvv70077+977+977+9HR1C77+977+977+9Ze+/vSLvv70oHu+/vQTvv70G77+9woIFG3zvv70dS++/ve+/ve+/vR0R77+977+9FCkEQwIVCTAc77+977+9wpHdge+/ve+/ve+/vXsCde+/ve+/vU8RSO+/vSBA77+977+977+9w4gJKO+/vUHvv73vv73vv71EKhzvv70qAUJIC++/ve+/vVYR77+977+977+9250zc3YoSEzvv73vv73vv70eH3Lvv70rEO+/ve+/vQ3MmVfvv73vv73vv73Er++/vXliIu+/vTbNuzTvv70VPETvv71uHe+/vQHvv73vv73vv71oXwIdJu+/ve+/ve+/vQQ877+977+9Cu+/vQfvv73vv70rVu+/ve+/vXbvv73vv73vv73vv70i77+977+9Vn4HNxjvv73vv70X77+9OEJhd2Lvv73vv73vv73vv73Qh8K777+9VQzvv70L77+977+9Su+/ve+/vXMM77+9GwlK77+977+977+9e++/vWXvv70aTT/vv71gSHfvv73vv71iEhHvv73vv71/UO+/vREBdD1QUe+/vQNp77+9ZVfvv70DRUgm77+9fd6lIu+/vREV77+977+977+9Su+/ve+/vRTGh++/ve+/vUAFBSAmKO+/vQDvv70R77+9ADnvv73vv70BL1Tvv71UMBxY77+9ISYB77+9d2Pvv70677+977+9QhEO77+9ICTvv71GUngNTjDvv70h77+9HAfvv73vv71DWWTvv73vv73vv71hIe+/vVABW0Pvv70g77+977+9KB7vv73vv70Q77+977+977+977+977+9HQkC3Z7vv73vv70m77+9QiQA77+977+9chrvv71OUl0i77+9J++/ve+/vRrvv73vv73vv70IemLvv73vv70UQe+/vU1C77+977+9zKLvv70277+9KO+/vT9ICu+/vXfHvSDvv71E77+9Je+/vWkZ77+977+9AQXvv73vv70q66y077+9UO+/ve+/ve+/ve+/vRZpIe+/ve+/vVdqKhLvv70j77+9KhPvv73vv70THe+/ve+/vVbvv71MIe+/vTrvv73vv73vv71c77+9X++/ve+/vXwiIG82Su+/vU8R77+9Cu+/ve+/vX3CnDpsKgDvv71gbBTvv70s77+977+9IRXXou+/vSfvv73vv70e77+966+q77+9W++/vQvlnpvvv70gG0LXmBJh77+977+9I++/ve+/ve+/vRLvv70FHe+/vSLvv70M77+977+9UO+/vV3vv70UW++/ve+/ve+/vSnvv70ECX0mGBzvv70x77+91KMc77+9Pu+/vQt6NTZMBAkBC++/ve+/vQAxUBnSlHPvv73vv73vv70uO++/vTnvv71XGyTvv73vv71277+9fCzvv71wU++/vTzvv70uFO+/ve+/vWnvv70bG++/vULvv70h77+9Je+/vR0NLWDvv73Rhe+/ve+/ve+/vShEYBBSCe+/ve+/ve+/vWRDO2wwNe+/vT0w77+9WGsF77+9Ze+/vXBdDx1TBDvvv70lVmljTu+/ve+/vRBrGVLvv712Le+/ve+/vXVUH++/ve+/ve+/ve+/ve+/vQlA77+9ICfvv73vv73vv70D77+977+9TCzvv71Z77+9045X77+977+9NEvvv71A77+9ZDBb77+9OR0x77+9IO+/ve+/vX1j77+9Ai8s77+977+977+96qy3TkUQADs=\" width=\"110\" height=\"45\">");
   }

   @Test
   public void testExpression() {
      ImageTag tag = new ImageTag();
      ImageExpression eBayLogo = new ResourceExpressionParser(getExpressionEnv()).parse("${res.img.local.half.eBayLogo_gif}");
      ImageTagModel model = tag.getModel();

      model.setValue(eBayLogo);

      checkTag(tag, "<img src=\"/test/img/half/eBayLogo.gif\" width=\"110\" height=\"45\">");
   }

   @Test
   public void testLocalImageSecureUrl() {
      ImageTag tag = new ImageTag();
      ImageTagModel model = tag.getModel();

      model.setValue(ImageFactory.forRef().createLocalRef("/half/eBayLogo.gif"));
      model.setSecure(true);

      checkTag(tag, "<img src=\"/test/img/half/eBayLogo.gif\" width=\"110\" height=\"45\">");
   }

   @Test
   public void testLocalImageUrl() {
      ImageTag tag = new ImageTag();
      ImageTagModel model = tag.getModel();

      model.setValue(ImageFactory.forRef().createLocalRef("/half/eBayLogo.gif"));

      checkTag(tag, "<img src=\"/test/img/half/eBayLogo.gif\" width=\"110\" height=\"45\">");
   }

   @Test
   public void testPicsImageSecureUrl() {
      ImageTag tag = new ImageTag();
      ImageTagModel model = tag.getModel();

      model.setValue(ImageFactory.forRef().createPicsRef("/half/btnSearch.gif"));
      model.setSecure(true);

      checkTag(tag, "<img src=\"https://pics.ebaystatic.com/aw/pics/half/btnSearch.gif\""
            + " width=\"72\" height=\"37\">");
   }

   @Test
   public void testPicsImageUrl() {
      ImageTag tag = new ImageTag();
      ImageTagModel model = tag.getModel();

      model.setValue(ImageFactory.forRef().createPicsRef("/half/btnSearch.gif"));

      checkTag(tag, "<img src=\"http://pics.ebaystatic.com/aw/pics/half/btnSearch.gif\""
            + " width=\"72\" height=\"37\">");
   }

   @Test
   public void testSharedImageSecureUrl() {
      ImageTag tag = new ImageTag();
      ImageTagModel model = tag.getModel();

      model.setValue(ImageFactory.forRef().createSharedRef("/half/eBayLogo.gif"));
      model.setSecure(true);

      checkTag(tag, "<img src=\"https://res.unidal.org/img/half/eBayLogo.gif\" width=\"110\" height=\"45\">");
   }

   @Test
   public void testSharedImageUrl() {
      ImageTag tag = new ImageTag();
      ImageTagModel model = tag.getModel();

      model.setValue(ImageFactory.forRef().createSharedRef("/half/eBayLogo.gif"));

      checkTag(tag, "<img src=\"http://res.unidal.org/img/half/eBayLogo.gif\" width=\"110\" height=\"45\">");
   }
}
