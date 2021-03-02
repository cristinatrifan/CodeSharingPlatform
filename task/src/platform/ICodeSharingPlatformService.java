package platform;

import java.util.List;

public interface ICodeSharingPlatformService {
    List<ICodeSharingPlatformProjection> getLatestCodeSnippets();
    ICodeSharingPlatformProjection getUUIDCodeSnippet(String N) throws Exception;
    String createNewCodeSnippet(String newCode, Integer time, Integer views);
    Boolean updateOrDeleteCodeSnippet(String UUID) throws Exception;
    Boolean checkDeletionStatus();
}
