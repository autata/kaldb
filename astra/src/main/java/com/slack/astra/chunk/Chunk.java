package com.slack.astra.chunk;

import com.slack.astra.logstore.search.SearchQuery;
import com.slack.astra.logstore.search.SearchResult;
import com.slack.astra.metadata.schema.FieldType;
import java.io.Closeable;
import java.util.Map;

/**
 * A chunk stores messages for a specific time range. It can concurrently store messages and respond
 * to queries.
 */
public interface Chunk<T> extends Closeable {

  /** A string that uniquely identifies this chunk. */
  String id();

  /** Metadata about the loaded chunk if available, else null. */
  ChunkInfo info();

  /**
   * Returns search results for the provided query. If no chunk data exists will return an empty
   * result.
   */
  SearchResult<T> query(SearchQuery query);

  /** Return true if the chunk contains data within that time range (epoch ms). */
  boolean containsDataInTimeRange(long startTs, long endTs);

  Map<String, FieldType> getSchema();
}
